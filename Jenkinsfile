pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout aymen_pipeline'
                sh 'git pull origin aymen_pipeline'
            }
        }

        stage("Test maven") {
            steps {
                sh 'mvn test'
            }
        }

        stage("Build artifact") {
            steps {
                sh 'mvn package'
            }
        }
    }
}

