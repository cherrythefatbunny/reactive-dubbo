#!/usr/bin/env bash
mvn -Dgatling.simulationClass=simulations.blocking -DSIM_USERS=100  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.mvc -DSIM_USERS=1000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.mvc -DSIM_USERS=2000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.mvc -DSIM_USERS=3000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.async -DSIM_USERS=1000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.async -DSIM_USERS=2000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.async -DSIM_USERS=3000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.future -DSIM_USERS=1000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.future -DSIM_USERS=2000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.future -DSIM_USERS=3000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.future -DSIM_USERS=4000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactivefuture -DSIM_USERS=1000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactivefuture -DSIM_USERS=2000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactivefuture -DSIM_USERS=3000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactivefuture -DSIM_USERS=4000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactive -DSIM_USERS=1000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactive -DSIM_USERS=2000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactive -DSIM_USERS=3000  gatling:test
sleep 30
mvn -Dgatling.simulationClass=simulations.reactive -DSIM_USERS=4000  gatling:test
