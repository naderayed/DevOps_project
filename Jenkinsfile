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
                sh 'mvn clean'
                sh 'mvn test'
                sh 'mvn package'
            }
        }

        stage("SonarQube analysis") {
            steps {
                withSonarQubeEnv('sonarQubeServer') {
                    sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent -Djacoco.version=0.8.8 test sonar:sonar'

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
                        sh "docker push $DOCKER_IMAGE"
                    
            }
        }
    }
}
