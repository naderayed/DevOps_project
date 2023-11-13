pipeline {
        agent any
    stages {
        stage("Git Pulling Stage") {
            steps {
                sh 'git checkout nader'
                sh 'git pull origin nader'
            }
        }

        stage("Cleaning Stage") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn clean"
                }
            }
        }

        stage("Testing Stage") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn test"
                }
            }
        }

        stage('SonarQube Scanning') {
            steps {
                withMaven(maven: 'mvn') {
                    withSonarQubeEnv('sonarQubeServer') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }

        stage("Building Stage") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn package"
                }
            }
        }

        stage("Nexus Deploy Stage") {
            steps {
                withMaven(maven: 'mvn') {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }

        stage('Docker Build Stage') {
            steps {
             sh 'docker build -t nader-devops-img .'
            }
        }

     stage('Push Image Stage') {
         steps {
             withDockerRegistry([ credentialsId: "dockerhubCredentials", url: "" ]) {
                      sh 'docker push nader-devops-img'
                }
            }
        }
    }
}
