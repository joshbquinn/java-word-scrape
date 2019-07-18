node {

    stage('SCM'){
        checkout scm
    }

    stage('Build') {
        sh 'mvn clean package'
    }

    stage('Test')

    stage('Archive') {

        archiveArtifacts allowEmptyArchive: true, artifacts: '*.txt'
        archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar'

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