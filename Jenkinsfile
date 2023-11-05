pipeline {
    agent any

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
        stage("Build docker image") {
            steps {
                sh 'sudo chmod 666 /var/run/docker.sock'
                sh 'docker build -t springimage .'
            }
        }
    }
}

