pipeline {
    agent any
    
    stages() {
        stage('git clone') {
            steps() {
                git 'https://github.com/banananaj/cicd-test.git'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        
        stage('execute sh') {
            steps {
                sh "chmod 774 ./Scripts/deploy.sh"
                sh "./Scripts/deploy.sh"
            }
        }        
    }
}
