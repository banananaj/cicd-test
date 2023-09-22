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
                dockerImage = docker.build imagename
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }

        stage('Push Docker') {
          steps {
            echo 'Push Docker'
            script {
                docker.withRegistry( '', registryCredential) {
                    dockerImage.push()
                }
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }

        stage('Docker Run') {
            steps {
                echo 'Pull Docker Image & Docker Image Run'
                sshagent (credentials: ['SSH Credential ID -> ssh']) {
                    sh "ssh -o StrictHostKeyChecking=no [Spring Boot Server username]@[Spring Boot Server IP 주소] 'docker pull [도커이미지 이름]'"
                    sh "ssh -o StrictHostKeyChecking=no [Spring Boot Server username]@[Spring Boot Server IP 주소] 'docker ps -q --filter name=[컨테이너 이름] | grep -q . && docker rm -f \$(docker ps -aq --filter name=[컨테이너 이름])'"
                    sh "ssh -o StrictHostKeyChecking=no [Spring Boot Server username]@[Spring Boot Server IP 주소] 'docker run -d --name [컨테이너 이름] -p 8080:8080 [도커이미지 이름]'"
                }
            }
        }
    }
}

