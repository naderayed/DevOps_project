pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout marco'
                sh 'git pull origin marco'
            }
        }
 stage("MVN Compile") {
            steps {

                    sh "mvn clean compile"

            }
        }


        stage("Run Tests") {
            steps {

                    sh "mvn test"

            }
        }

         stage('SonarQube Code Analyse') {
                    steps {
                            withSonarQubeEnv('SonarqubeServer') {
                                sh 'mvn sonar:sonar'
                            }
                    }
                  }

        

          stage("Nexus Deployment") {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

    }
}
