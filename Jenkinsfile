node {
    notify('CI')
    try {
        stage('Source Control Checkout') {
            cehckout scm
        }

        stage('Test'){
            sh mvn
        }

        stage('package') {
            sh 'mvn clean package'
        }
        stage('archive') {
            archiveArtifacts allowEmptyArchive: true, artifacts: '*.txt'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar'
        }

    }catch(err){
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