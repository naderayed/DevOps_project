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
            }
        }

        stage("Generate and Confirm JaCoCo Report") {
            steps {
                sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent -Djacoco.version=0.8.8 test jacoco:report'
                script {
                    // Check if the JaCoCo report file exists
                    def jacocoReportPath = 'target/site/jacoco/jacoco.xml'
                    if (fileExists(jacocoReportPath)) {
                        echo "JaCoCo report generated successfully at: ${jacocoReportPath}"
                    } else {
                        error "Failed to generate JaCoCo report. File not found at: ${jacocoReportPath}"
                    }
                }
            }
        }

        stage("Build Artifact") {
            steps {
                sh 'mvn package'
            }
        }

        stage("SonarQube analysis") {
            steps {
                withSonarQubeEnv('sonarQubeServer') {
                    // Use sonar.jacoco.reportPaths to specify the location of the JaCoCo XML report
                    sh "mvn sonar:sonar -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml"
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

