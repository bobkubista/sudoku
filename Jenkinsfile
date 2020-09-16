pipeline { 
    agent any  
    tools { 
        maven 'Maven' 
        jdk '1.8' 
    }
    stages { 
    	stage('auto formatting') {
    	    steps {
				sh 'mvn spotless:apply'
				sh 'mvn -Dmessage="auto formatting" scm:checkin'    	        
    	    }
		}
        stage('Compile') { 
            steps { 
               sh 'mvn clean compile'
            }
        }
        stage('Test'){
        	steps {
        		sh 'mvn test'
        	}
        	post {
                always {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
        stage('Sonar') {
        	steps {
        	    sh 'mvn sonar:sonar'
        	}
        }
    }
}
