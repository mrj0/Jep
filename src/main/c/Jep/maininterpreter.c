/*
   jep - Java Embedded Python

   Copyright (c) 2016-2022 JEP AUTHORS.

   This file is licensed under the the zlib/libpng License.

   This software is provided 'as-is', without any express or implied
   warranty. In no event will the authors be held liable for any
   damages arising from the use of this software.

   Permission is granted to anyone to use this software for any
   purpose, including commercial applications, and to alter it and
   redistribute it freely, subject to the following restrictions:

   1. The origin of this software must not be misrepresented; you
   must not claim that you wrote the original software. If you use
   this software in a product, an acknowledgment in the product
   documentation would be appreciated but is not required.

   2. Altered source versions must be plainly marked as such, and
   must not be misrepresented as being the original software.

   3. This notice may not be removed or altered from any source
   distribution.
*/

#include "Jep.h"
#include "jep_MainInterpreter.h"


// -------------------------------------------------- jni functions


/*
 * Class:     jep_MainInterpreter
 * Method:    initializePython
 * Signature: ([Ljava/lang/String;IILjava/lang/String;ILjava/lang/String;IIIII)V
 */
JNIEXPORT void JNICALL Java_jep_MainInterpreter_initializePython
(JNIEnv *env,
 jclass class,
 jobjectArray argv,
 jint hashSeed,
 jint useHashSeed,
 jstring home,
 jint optimizationLevel,
 jstring programName,
 jint siteImport,
 jint useEnvironment,
 jint userSiteDirectory,
 jint verbose,
 jint writeByteCode
)
{
    pyembed_startup(env, argv, hashSeed, useHashSeed, home, optimizationLevel,
                    programName, siteImport, useEnvironment, userSiteDirectory, verbose,
                    writeByteCode);
}

/*
 * Class:     jep_MainInterpreter
 * Method:    sharedImportInternal
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_jep_MainInterpreter_sharedImportInternal
(JNIEnv *env, jclass class, jstring module)
{
    pyembed_shared_import(env, module);
}

