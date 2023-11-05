pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Update with your credential ID
        DOCKER_IMAGE = 'aymenkhairoune/springimage:latest' // Update with your Docker Hub repository and tag
    }

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
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }
        stage("Push to Docker Hub") {
            steps {
                withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                    sh "docker push $DOCKER_IMAGE"
                }
            }
        }
    }
}

