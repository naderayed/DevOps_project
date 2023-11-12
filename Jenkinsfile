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
                sh 'mvn deploy -DskipTests'
            }
        }

        stage("Build Docker image") {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

    //    stage("Push Docker image to Docker Hub") {
    //        steps {
    //            sh "docker push $DOCKER_IMAGE"
    //        }
    //    }
        
        stage('Run Docker Compose') {
    steps {
        script {
            sh 'docker compose up -d mysqldb'  // Start only the MySQL container
            sleep 30  // Wait for 30 seconds (adjust as needed)
            sh 'docker compose up -d backend-spring'  // Start the Spring Boot application
        }
    }
}
    }
}

