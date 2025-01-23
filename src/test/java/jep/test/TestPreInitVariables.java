package jep.test;

import jep.Interpreter;
import jep.JepException;
import jep.MainInterpreter;
import jep.PyConfig;
import jep.SharedInterpreter;

/**
 * A test class for verifying that Jep can correctly configure the global Python
 * interpreter with specific flags before actually starting the interpreter.
 * This test exercises PyConfig options to match Python command line arguments
 * and checks the output against sys.flags.
 * 
 * TODO: If you wanted to be extra-thorough you could write tests that don't
 * explicitly trust sys.flags, e.g. verify that when setting
 * Py_DontWriteBytecodeFlag that a .pyc or .pyo is not actually written out.
 * 
 * Created: June 2016
 * 
 * @author Nate Jensen
 * @since 3.6
 * @see "https://github.com/ninia/jep/issues/49"
 */
public class TestPreInitVariables {

    public static void main(String[] args) throws JepException {
        PyConfig pyConfig = new PyConfig();
        // pyConfig.setIgnoreEnvironmentFlag(1);
        // TODO fix test so no site flag can be tested
        // pyConfig.setNoSiteFlag(1);
        pyConfig.setUserSiteDirectory(false);
        // verbose prints out too much, when it's on, it's clear it's on
        // pyConfig.setVerboseFlag(1);
        pyConfig.setOptimizationLevel(1);
        pyConfig.setWriteBytecode(false);
        pyConfig.setUseHashSeed(false);
        MainInterpreter.setInitParams(pyConfig);
        try (Interpreter interp = new SharedInterpreter()) {
            interp.eval("import sys");
            // assert 1 == ((Number)
            // jep.getValue("sys.flags.ignore_environment"))
            // .intValue();
            assert 0 == ((Number) interp.getValue("sys.flags.no_site"))
                    .intValue();
            assert 1 == ((Number) interp.getValue("sys.flags.no_user_site"))
                    .intValue();
            assert 0 == ((Number) interp.getValue("sys.flags.verbose"))
                    .intValue();
            assert 1 == ((Number) interp.getValue("sys.flags.optimize"))
                    .intValue();
            assert 1 == ((Number) interp
                    .getValue("sys.flags.dont_write_bytecode")).intValue();
            assert 1 == ((Number) interp
                    .getValue("sys.flags.hash_randomization")).intValue();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

}
