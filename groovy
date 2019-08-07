#!/usr/bin/env groovy
import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL

node{
    stage('pull code'){
	git 'https://github.com/Githubkiruba/DevOpsClassCodes.git'
    }
    stage ("ABK-Compile"){
	withMaven(maven: 'KirshMaven'){
                sh 'mvn compile'
        }
    } 
    stage ("ABK-Review"){
        withMaven(maven: 'KirshMaven'){
                sh 'mvn pmd:pmd'
        }
    }
    stage ("ABK-Test"){
        withMaven(maven: 'KirshMaven'){
                sh 'mvn test'
        }
    }     	
    stage ("ABK-Coverage"){
        withMaven(maven: 'KirshMaven'){
                sh 'cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '/target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnheal                    thy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCo                    verageChart: false''
        }
    }	
    stage ("ABK-Package"){
        withMaven(maven: 'KirshMaven'){
                sh 'package'
        }
    }
}
