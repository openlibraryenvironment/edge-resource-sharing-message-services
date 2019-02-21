#!groovy

node {

  checkout scm

  stage 'test' {
    sh './gradlew clean test'
  }

  stage 'build'{
    sh './gradlew assemble'
  }

  stage 'archive' {
    sh 'ls build/libs'
    archiveArtifacts artifacts: 'build/libs/resource-sharing-message-services-*'
  }
  // step([$class: 'ArtifactArchiver', artifacts: 'build/libs/*.jar', fingerprint: true])
  // 
  // stage 'reports'
  // step([$class: 'JUnitResultArchiver', testResults: 'build/test-results/*.xml'])
}
