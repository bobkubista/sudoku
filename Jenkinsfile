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
    stage('Build, test, analyse') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh 'mvn -B -ntp verify sonar:sonar'
        }
      }
    }
    stage('Quality gate') {
      steps {
        timeout(time: 10, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: true   // SonarQube calls back via its webhook
        }
      }
    }
  }
}
