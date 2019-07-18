node {
    try {
        agent {
            docker {
                image 'maven:3-alpine',
                        args '-v /root/.m2:/root/.m2'
            }
        }
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


    } catch (err) {
        notify("Error ${err}")
        currentBuild.result = 'Failure'
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