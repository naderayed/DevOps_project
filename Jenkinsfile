pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-credentials')
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
                sh 'mvn clean'
                sh 'mvn test'
                sh 'mvn package'
            }
        }

        stage("SonarQube analysis") {
            steps {
                withSonarQubeEnv('sonarQubeServer') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage("Deploy to Nexus") {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

        stage("Build Docker image") {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }
        stage("Push Docker image to Docker Hub") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                        sh "docker push $DOCKER_IMAGE"
                    }
            }
        }
    }
}
}
