pipeline {
    agent any
    environment {
        imagename = "cicd-test"
        registryCredential = 'dckr_pat_vF0iX9Bc26zq_6t-0r9LJYRRx8c'
        dockerImage = ''
    }

    stages {
        stage('Prepare') {
          steps {
            echo 'Clonning Repository'
            git url: 'https://github.com/banananaj/cicd-test.git',
              branch: 'main'
            }
        }


        stage('Bulid Gradle') {
          steps {
            echo 'Bulid Gradle'
            dir('.'){
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
          }
        }

        stage('Bulid Docker') {
          steps {
            echo 'Bulid Docker'
            script{
                dockerImage = docker.build("cicd-test:latest", "-f Dockerfile .")
            }
          }
        }


        stage('Docker Run') {
            steps {
                echo 'Pull Docker Image & Docker Image Run'
                script{
                    docker.image('cicd-test:latest').withRun('-d -p 5000:80')
                }
            }
        }
    }
}

