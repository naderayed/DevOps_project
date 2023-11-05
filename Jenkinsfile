pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout aymen_pipeline'
                sh 'git pull origin aymen_pipeline'
            }
        }
        stage("Build artifact") {
            steps {
                sh 'mvn package'
            }
        }
        stage("Build Docker image") {
            steps {
                script {
                    // Start a Docker-in-Docker container
                    docker.image('docker:20.10').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
                        sh 'docker build -t springimage .'
                    }
                }
            }
        }
    }
}
