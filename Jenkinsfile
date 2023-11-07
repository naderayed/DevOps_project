pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout marco'
                sh 'git pull origin marco'
            }
        }

          stage("Nexus Deployment") {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}
