pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'aymenkhairoune/springimage:latest'
    }

    stages {
        stage("Git") {
            steps {
                sh 'git checkout aymen_pipeline'
                sh 'git pull origin aymen_pipeline'
            }
        }

        stage("Clean and Build") {
            steps {
                sh 'mvn package'
            }
        }
        stage("Build Docker image") {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }
        stage("Push Docker image to Docker Hub") {
            steps {
                        sh "docker push $DOCKER_IMAGE"
                    
            }
        }
    }
}
