pipeline {
    agent {
        docker {
            image 'docker:20.10'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
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
        stage("Build docker image") {
            steps {
                sh 'docker build -t springimage .'
            }
        }
    }
}
