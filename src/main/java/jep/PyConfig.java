/**
 * Copyright (c) 2016-2024 JEP AUTHORS.
 *
 * This file is licensed under the the zlib/libpng License.
 *
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any
 * damages arising from the use of this software.
 * 
 * Permission is granted to anyone to use this software for any
 * purpose, including commercial applications, and to alter it and
 * redistribute it freely, subject to the following restrictions:
 * 
 *     1. The origin of this software must not be misrepresented; you
 *     must not claim that you wrote the original software. If you use
 *     this software in a product, an acknowledgment in the product
 *     documentation would be appreciated but is not required.
 * 
 *     2. Altered source versions must be plainly marked as such, and
 *     must not be misrepresented as being the original software.
 * 
 *     3. This notice may not be removed or altered from any source
 *     distribution.
 */
package jep;

import java.util.Arrays;

/**
 * <p>
 * A configuration object for setting Python initialization parameters.
 * </p>
 * <p>
 * These options map directly to the options documented at
 * https://docs.python.org/3/c-api/init_config.html#pyconfig
 * </p>
 * <p>
 * This class was rewritten with the introduction of
 * <a href="https://peps.python.org/pep-0587/">PEP-587</a>. It contains many
 * deprecated methods that reference the Python c-api available before PEP-587
 * was implemented and newer replacement methods that reference the current
 * configuration c-api described in PEP-587. Although all methods still work the
 * newer methods should be preferred and the deprecated methods will be removed
 * in the future.
 * </p>
 *
 * @since 3.6
 */
public class PyConfig {

    /*
     * -1 is used to indicate not set, in which case we will not set it in the
     * native code and the setting will be Python's default. A value of 0 or
     * greater will cause the value to be set in the native code.
     */

    protected String[] argv;

    protected int hashSeed = -1;

    protected int useHashSeed = -1;

    protected String home;

    protected int optimizationLevel = -1;

    protected String programName;

    protected int siteImport = -1;

    protected int useEnvironment = -1;

    protected int userSiteDirectory = -1;

    protected int verbose = -1;

    protected int writeBytecode = -1;

    /**
     * Set sys.argv for {@link SharedInterpreter}s and shared modules used by
     * {@link SubInterpreter}s.
     *
     * @param argv
     *            command line arguments
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig_SetArgv
     * 
     */
    public PyConfig setArgv(String... argv) {
        this.argv = argv;
        return this;
    }

    /**
     * If {@link #setUseHashSeed(boolean)} is false this value is ignored but if
     * it is true this is a fixed seed for generating the hash() of the types
     * covered by the hash randomization.
     * 
     * @param hashSeed
     *            a fixed seed to use for generating hash() values
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.hash_seed
     */
    public PyConfig setHashSeed(int hashSeed) {
        this.hashSeed = hashSeed;
        return this;
    }

    /**
     * If this is set to true the value of {@link #hashSeed} is used as a fixed
     * seed for generating the hash() of the types covered by the hash
     * randomization.
     * 
     * @param useHashSeed
     *            a boolean to indicate whether a fixed seed should be used.
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.use_hash_seed
     */
    public PyConfig setUseHashSeed(boolean useHashSeed) {
        this.useHashSeed = useHashSeed ? 1 : 0;
        return this;
    }

    /**
     * Set the default Python "home" directory, that is, the location of the
     * standard Python libraries.
     * 
     * @param home
     *            the directory tpuse for Python home.
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.home
     */
    public PyConfig setHome(String home) {
        this.home = home;
        return this;
    }

    /**
     * Set the compilation optimization level:
     * <li>0: Peephole optimizer, set __debug__ to True.</li>
     * <li>1: Level 0, remove assertions, set __debug__ to False.</li>
     * <li>2: Level 1, strip docstrings.</li>
     * 
     * @param optimizationLevel
     *            the optimization level to use.
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.optimization_level
     */
    public PyConfig setOptimizationLevel(int optimizationLevel) {
        this.optimizationLevel = optimizationLevel;
        return this;
    }

    /**
     * Set the program name used to initialize executable and in early error
     * messages during Python initialization.
     * 
     * @param programName
     *            the name of the executable Python program.
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.program_name
     */
    public PyConfig setProgramName(String programName) {
        this.programName = programName;
        return this;
    }

    /**
     * Import the site module at startup?
     * 
     * If false, disable the import of the module site and the site-dependent
     * manipulations of sys.path that it entails.
     * 
     * Also disable these manipulations if the site module is explicitly
     * imported later (call site.main() if you want them to be triggered).
     * 
     * @param siteImport
     *            whether to enable import of site module.
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.site_import
     */
    public PyConfig setSiteImport(boolean siteImport) {
        this.siteImport = siteImport ? 1 : 0;
        return this;
    }

    /**
     * Use environment variables?
     * 
     * If false, ignore the environment variables.
     * 
     * @param useEnvironment
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.use_environment
     */
    public PyConfig setUseEnvironment(boolean useEnvironment) {
        this.useEnvironment = useEnvironment ? 1 : 0;
        return this;
    }

    /**
     * If true, add the user site directory to sys.path.
     * 
     * @param userSiteDirectory
     *            whether to add user site directory to the path.
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.user_site_directory
     */
    public PyConfig setUserSiteDirectory(boolean userSiteDirectory) {
        this.userSiteDirectory = userSiteDirectory ? 1 : 0;
        return this;
    }

    /**
     * Verbose mode. If greater than 0, print a message each time a module is
     * imported, showing the place (filename or built-in module) from which it
     * is loaded.
     * 
     * If greater than or equal to 2, print a message for each file that is
     * checked for when searching for a module. Also provides information on
     * module cleanup at exit.
     * 
     * @param verbose
     *            the level of verbose
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.verbose
     */
    public PyConfig setVerbose(int verbose) {
        this.verbose = verbose;
        return this;
    }

    /**
     * If false, Python won’t try to write .pyc files on the import of source
     * modules.
     * 
     * @param writeBytecode
     *            whether to write bytecode files.
     * @return a reference to this PyConfig
     * @see https://docs.python.org/3/c-api/init_config.html#c.PyConfig.write_bytecode
     */
    public PyConfig setWriteBytecode(boolean writeBytecode) {
        this.writeBytecode = writeBytecode ? 1 : 0;
        return this;
    }

    /**
     * Set the Py_NoSiteFlag variable on the python interpreter. This
     * corresponds to the python "-S" flag and will prevent the "site" module
     * from being automatically loaded.
     * 
     * @param noSiteFlag
     *            value to pass to Python for Py_NoSiteFlag
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setSiteImport(int)} instead.
     */
    @Deprecated
    public PyConfig setNoSiteFlag(int noSiteFlag) {
        this.siteImport = (noSiteFlag == 0) ? 1 : 0;
        return this;
    }

    /**
     * Set the Py_NoUserSiteDirectory variable on the python interpreter. This
     * corresponds to the python "-s" flag and will prevent the user's local
     * python site directory from being added to sys.path.
     * 
     * @param noUserSiteDirectory
     *            value to pass to Python for Py_NoUserSiteDirectory
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setUserSiteDirectory(int)} instead.
     */
    @Deprecated
    public PyConfig setNoUserSiteDirectory(int noUserSiteDirectory) {
        this.userSiteDirectory = (noUserSiteDirectory == 0) ? 1 : 0;
        return this;
    }

    /**
     * Set the Py_IgnoreEnvironmentFlag variable on the python interpreter. This
     * corresponds to the python "-E" flag and will instruct python to ignore
     * all PYTHON* environment variables (e.g. PYTHONPATH).
     * 
     * @param ignoreEnvironmentFlag
     *            value to pass to Python for Py_IgnoreEnvironmentFlag
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setUseEnvironment(int)} instead.
     */
    @Deprecated
    public PyConfig setIgnoreEnvironmentFlag(int ignoreEnvironmentFlag) {
        this.useEnvironment = (ignoreEnvironmentFlag == 0) ? 1 : 0;
        return this;
    }

    /**
     * Set the Py_VerboseFlag variable on the python interpreter. This
     * corresponds to the python "-v" flag and will increase verbosity, in
     * particular tracing import statements.
     * 
     * @param verboseFlag
     *            value to pass to Python for Py_VerboseFlag
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setVerbose(int)} instead.
     */
    @Deprecated
    public PyConfig setVerboseFlag(int verboseFlag) {
        this.verbose = verboseFlag;
        return this;
    }

    /**
     * Set the Py_OptimizeFlag variable on the python interpreter. This
     * corresponds to the python "-O" flag and will slightly optimize the
     * generated bytecode.
     * 
     * @param optimizeFlag
     *            value to pass to Python for Py_OptimizeFlag
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setOptimizationLevel(int)} instead.
     */
    @Deprecated
    public PyConfig setOptimizeFlag(int optimizeFlag) {
        this.optimizationLevel = optimizeFlag;
        return this;
    }

    /**
     * Set the Py_DontWriteBytecodeFlag variable on the python interpreter. This
     * corresponds to the python "-B" flag and will instruct python to not write
     * .py[co] files on import.
     * 
     * @param dontWriteBytecodeFlag
     *            value to pass to Python for Py_DontWriteBytecodeFlag
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setWriteBytecode(int)} instead.
     */
    @Deprecated
    public PyConfig setDontWriteBytecodeFlag(int dontWriteBytecodeFlag) {
        this.writeBytecode = (dontWriteBytecodeFlag == 0) ? 1 : 0;
        return this;
    }

    /**
     * Set the Py_HashRandomizationFlag variable on the python interpreter. This
     * corresponds to the environment variable PYTHONHASHSEED.
     * 
     * @param hashRandomizationFlag
     *            value to pass to Python for Py_HashRandomizationFlag
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setHashSeed(int)} and
     *             {@link #setUseHashSeed(int)} instead.
     */
    @Deprecated
    public PyConfig setHashRandomizationFlag(int hashRandomizationFlag) {
        if (hashRandomizationFlag == 0) {
            this.useHashSeed = 0;
        } else {
            this.hashSeed = hashRandomizationFlag;
            this.useHashSeed = 1;
        }
        return this;
    }

    /**
     * Set the home location on the python interpreter. This is the location of
     * the standard python libraries. This corresponds to the environment
     * variable PYTHONHOME.
     * 
     * @param pythonHome
     *            the home location of the python installation
     * @return a reference to this PyConfig
     * @deprecated Use {@link #setHome(String)} instead.
     */
    @Deprecated
    public PyConfig setPythonHome(String pythonHome) {
        this.home = pythonHome;
        return this;
    }

    @Override
    public String toString() {
        return "PyConfig [argv=" + Arrays.toString(argv) + ", hashSeed="
                + hashSeed + ", useHashSeed=" + useHashSeed + ", home=" + home
                + ", optimizationLevel=" + optimizationLevel + ", programName="
                + programName + ", siteImport=" + siteImport
                + ", useEnvironment=" + useEnvironment + ", userSiteDirectory="
                + userSiteDirectory + ", verbose=" + verbose
                + ", writeBytecode=" + writeBytecode + "]";
    }

}
