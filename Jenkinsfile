pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout marco'
                sh 'git pull origin marco'
            }
        }

        stage("MVN Clean") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn clean"
                }
            }
        }

        stage("MVN Compile") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn clean compile"
                }
            }
        }

        stage("Run Tests") {
            steps {
                withMaven(maven: 'mvn') {
                    sh "mvn test"
                }
            }
        }

         stage('SonarQube Code Analyse') {
                    steps {
                            withSonarQubeEnv('SonarqubeServer') {
                                sh 'mvn sonar:sonar'
                            }
                    }
                  }

                    stage("Nexus Deploy ") {
                              steps {
                                  withMaven(maven: 'mvn') {
                                      sh 'mvn deploy -DskipTests'
                                  }
                              }
                          }
    }
}
