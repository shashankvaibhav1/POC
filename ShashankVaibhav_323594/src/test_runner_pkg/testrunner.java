package test_runner_pkg;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="FEATURES", glue="test_def_pkg")
public class testrunner extends AbstractTestNGCucumberTests{
  
}
