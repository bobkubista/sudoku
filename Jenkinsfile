// Built by the kvm-server Jenkins (Kubernetes agent pods, one build at a time).
// 'mvn verify' runs the tests, JaCoCo coverage and the Spotless format check;
// run 'mvn spotless:apply' locally when the format check fails.
pipeline {
  agent {
    kubernetes {
      defaultContainer 'maven'
      yaml '''
        spec:
          containers:
            - name: maven
              image: maven:3.9.16-eclipse-temurin-25
              command: ["cat"]
              tty: true
              resources:
                requests: { memory: 768Mi, cpu: 250m }
                limits: { memory: 1Gi }
            # explicit sizes for the default jnlp container, see kvm-server ADR-0041
            - name: jnlp
              resources:
                requests: { memory: 256Mi, cpu: 100m }
                limits: { memory: 512Mi }
      '''
    }
  }
  options {
    timestamps()
    disableConcurrentBuilds()
    buildDiscarder(logRotator(numToKeepStr: '10'))
    timeout(time: 30, unit: 'MINUTES')
  }
  stages {
    stage('Build and test') {
      when { not { branch 'master' } }
      steps {
        sh 'mvn -B -ntp verify'
      }
    }
    // SonarQube Community has no branch analysis: only master is analysed,
    // so a feature branch never overwrites master's result.
    stage('Build, test, analyse') {
      when { branch 'master' }
      steps {
        withSonarQubeEnv('SonarQube') {
          // the checkout belongs to the jnlp user; let git (Sonar blame) read it as root
          sh 'git config --global --add safe.directory "$WORKSPACE"'
          sh 'mvn -B -ntp verify sonar:sonar'
        }
      }
    }
    stage('Quality gate') {
      when { branch 'master' }
      steps {
        timeout(time: 10, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: true   // SonarQube calls back via its webhook
        }
      }
    }
  }
}
