node('windows') {
    try {

        stage('Checkout') {
            checkout scm
        }



        stage('Compile') {
            bat 'mvn clean compile -DskipTests'

            // Here I need to stash the pulled code
        }


        // Then I can unstash the pulled code within each node test block
        /*  stage('Test') {
              parallel 'linux': {
                  stage('Linux') {
                      node('ubuntu'){
                          sh 'mvn test'
                      }
                  }
              }, 'windows': {
                  stage('Windows') {
                      node('windows'){
                          bat 'mvn test'
                      }
                  }
              }
          }*/

        stage('Unit Tests'){
            bat 'mvn test'
        }

        stage('SonarQube Analysis'){
            def sonarScanner = tool 'SonarScanner'
            withSonarQubeEnv('SonarServer') {
                bat '${sonarScanner}\\bin\\sonar-scanner'
            }
        }

        stage('Package'){
            bat 'mvn package -DskipTests'
        }


        stage('Archive') {
            publishHTML(target: [allowMissing         : true,
                                 alwaysLinkToLastBuild: false,
                                 keepAll              : true,
                                 reportDir            : 'target/site/jacoco',
                                 reportFiles          : 'index.html',
                                 reportName           : 'Code Coverage',
                                 reportTitles         : ''])
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar'
        }

        stage('Run'){
            bat 'java -jar .\\target\\word-scraper-1.0-SNAPSHOT.jar "https://www.rte.ie/sport/golf/2019/0723/1064814-lowry-cant-wait-to-show-great-granny-the-claret-jug/"'
        }

        stage('Result'){
            archiveArtifacts allowEmptyArchive: true, artifacts:'/*.txt'
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