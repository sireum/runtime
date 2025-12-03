package org.sireum

object Init_Ext {
  def sireumApiRun(args: ISZ[String]): Z = {
    val sireumObject = Class.forName("org.sireum.Sireum$").getField("MODULE$").get(null)
    val sireumApiRun = Class.forName("org.sireum.SireumApi").getMethods.filter(m => m.getName == "run").head
    sireumApiRun.invoke(sireumObject, args).asInstanceOf[Z]
  }
}