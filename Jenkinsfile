pipeline {
    agent any
    environment{
        
    }
    stages {
        stage('github clone') {
            agent any
            steps {
                git branch: 'main',
                url: 'https://github.com/banananaj/cicd-test'
            }
            post {
                failure{
                    error "Fail Cloned Repository"
                }
            }
        }




    }
}

