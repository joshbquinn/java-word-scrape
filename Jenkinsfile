pipeline {
    agent any
    stages {
        stage('Git') {
            checkout scm
        }

        stage('Build') {
            sh 'mvn clean package'
        }


        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }


        stage('Archive') {
            archiveArtifacts allowEmptyArchive: true, artifacts: '*.txt'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar'
        }


    }
}

def notify(status){
    emailext(
            to: "jbqjenkins@gmail.com",
            subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body:"""<p>${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
    )
}