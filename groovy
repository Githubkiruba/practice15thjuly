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
                sh 'mvn compile '
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
}	
