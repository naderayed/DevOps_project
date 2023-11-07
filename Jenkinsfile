pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout marco'
                sh 'git pull origin marco'
            }
        }
 stage("MVN Compile") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn clean compile"
                }
            }
        }
          stage("Nexus Deployment") {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}
