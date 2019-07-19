node {
    try {
        stage('Git Checkout') {
            checkout scm
        }

        stage('Build - Compile, Test, Package') {
            sh 'mvn clean verify'
        }


        stage('Archival') {
            publishHTML(target: [allowMissing         : true,
                                 alwaysLinkToLastBuild: false,
                                 keepAll              : true,
                                 reportDir            : 'target/site/jacoco',
                                 reportFiles          : 'index.html',
                                 reportName           : 'Code Coverage',
                                 reportTitles         : ''])
            archiveArtifacts allowEmptyArchive: true, artifacts: '*.txt'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar'
        }

    } catch (err){
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