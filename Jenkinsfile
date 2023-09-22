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
            post {
             success {
               echo 'Successfully Cloned Repository'
             }
           	 failure {
               error 'This pipeline stops here...'
             }
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
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }

        stage('Bulid Docker') {
          steps {
            echo 'Bulid Docker'
            script {
                sh 'docker build -t cicd-test:latest .'
            }
          }
          post {
            failure {
              error 'pipline stop at build docker stage'
            }
          }
        }


        stage('Docker Run') {
            steps {
                echo 'Pull Docker Image & Docker Image Run'
                sh 'docker run -d -p 5000:80 cicd-test:latest'
            }
        }
    }
}

