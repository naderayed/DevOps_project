pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "aymenkhairoune/springimage:${env.BUILD_NUMBER}"
        NEXUS_URL = 'http://192.168.33.30:8081'
        NEXUS_REPOSITORY_PATH = '/repository/maven-releases/tn/'
        NEXUS_USERNAME = 'admin'
        NEXUS_PASSWORD = 'admin'
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
            }
        }

        stage("SonarQube analysis") {
            steps {
                withSonarQubeEnv('sonarQubeServer') {
                    sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent -Djacoco.version=0.8.8 test jacoco:report'
                    sh "mvn sonar:sonar -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml"
                }
            }
        }

        stage("Build Artifact") {
            steps {
                sh 'mvn package'
            }
        }

        stage("Deploy to Nexus") {
            steps {
                script {
                    def nexusApiUrl = "${NEXUS_URL}/service/rest/v1${NEXUS_REPOSITORY_PATH}"
                    sh "curl -X DELETE -u ${NEXUS_USERNAME}:${NEXUS_PASSWORD} ${nexusApiUrl}"
                    sh 'mvn deploy -DskipTests'
                }
            }
        }

        stage("Build Docker image") {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage("Push Docker image to Docker Hub") {
            steps {
                sh "docker push ${DOCKER_IMAGE}"
            }
        }

        stage('Run Docker Compose') {
        steps {
            script {
                sh "docker compose up -d mysqldb"
                sleep 30
                sh "docker compose up -d backend-spring-${BUILD_NUMBER}"
            }
        }
    }

        stage('Prometheus & Grafana') {
            steps {
                script {
                    sh 'docker start prometheus'
                    sh 'docker start grafana'
                }
            }
        }

        stage("Send Email Notification") {
            steps {
                script {
                    currentBuild.result = 'SUCCESS'
                    emailext(
                        subject: "Build #${currentBuild.number} Successful: ${currentBuild.fullDisplayName}",
                        body: """
                            The build was successful!
                            Build Details: ${BUILD_URL}
                            Build Number: ${currentBuild.number}
                            Build Status: ${currentBuild.currentResult}
                        """,
                        to: 'aymen.khairoune@esprit.tn'
                    )
                }
            }
        }
    }
}
