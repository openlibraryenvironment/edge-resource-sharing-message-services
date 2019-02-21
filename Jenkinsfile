#!groovy

node {

  stage('checkout') {
    checkout scm
  }

  stage ('test') {
    sh './gradlew --no-daemon --console=plain clean test'
  }

  stage ('build') {
    sh './gradlew --no-daemon --console=plain assemble'
  }

  stage ('archive') {
    sh 'ls build/libs'
    archiveArtifacts artifacts: 'build/libs/ki-rsms*'
  }
  // step([$class: 'ArtifactArchiver', artifacts: 'build/libs/*.jar', fingerprint: true])
  // 
  // stage 'reports'
  // step([$class: 'JUnitResultArchiver', testResults: 'build/test-results/*.xml'])
}
