// #Sireum
// @formatter:off

/*
 Copyright (c) 2021, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

// This file is auto-generated from Project.scala

package org.sireum.project

import org.sireum._
import org.sireum.Json.Printer._

object JSON {

  object Printer {

    @pure def printProject(o: Project): ST = {
      return printObject(ISZ(
        ("type", st""""Project""""),
        ("modules", printHashSMap(F, o.modules, printString _, printModule _)),
        ("poset", printPoset(T, o.poset, printString _))
      ))
    }

    @pure def printTargetType(o: Target.Type): ST = {
      val value: String = o match {
        case Target.Jvm => "Jvm"
        case Target.Js => "Js"
      }
      return printObject(ISZ(
        ("type", printString("Target")),
        ("value", printString(value))
      ))
    }

    @pure def printModule(o: Module): ST = {
      return printObject(ISZ(
        ("type", st""""Module""""),
        ("id", printString(o.id)),
        ("basePath", printString(o.basePath)),
        ("subPathOpt", printOption(T, o.subPathOpt, printString _)),
        ("deps", printISZ(T, o.deps, printString _)),
        ("targets", printISZ(F, o.targets, printTargetType _)),
        ("ivyDeps", printISZ(T, o.ivyDeps, printString _)),
        ("sources", printISZ(T, o.sources, printString _)),
        ("resources", printISZ(T, o.resources, printString _)),
        ("testSources", printISZ(T, o.testSources, printString _)),
        ("testResources", printISZ(T, o.testResources, printString _))
      ))
    }

  }

  @record class Parser(input: String) {
    val parser: Json.Parser = Json.Parser.create(input)

    def errorOpt: Option[Json.ErrorMsg] = {
      return parser.errorOpt
    }

    def parseProject(): Project = {
      val r = parseProjectT(F)
      return r
    }

    def parseProjectT(typeParsed: B): Project = {
      if (!typeParsed) {
        parser.parseObjectType("Project")
      }
      parser.parseObjectKey("modules")
      val modules = parser.parseHashSMap(parser.parseString _, parseModule _)
      parser.parseObjectNext()
      parser.parseObjectKey("poset")
      val poset = parser.parsePoset(parser.parseString _)
      parser.parseObjectNext()
      return Project(modules, poset)
    }

    def parseTargetType(): Target.Type = {
      val r = parseTargetT(F)
      return r
    }

    def parseTargetT(typeParsed: B): Target.Type = {
      if (!typeParsed) {
        parser.parseObjectType("Target")
      }
      parser.parseObjectKey("value")
      var i = parser.offset
      val s = parser.parseString()
      parser.parseObjectNext()
      Target.byName(s) match {
        case Some(r) => return r
        case _ =>
          parser.parseException(i, s"Invalid element name '$s' for Target.")
          return Target.byOrdinal(0).get
      }
    }

    def parseModule(): Module = {
      val r = parseModuleT(F)
      return r
    }

    def parseModuleT(typeParsed: B): Module = {
      if (!typeParsed) {
        parser.parseObjectType("Module")
      }
      parser.parseObjectKey("id")
      val id = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("basePath")
      val basePath = parser.parseString()
      parser.parseObjectNext()
      parser.parseObjectKey("subPathOpt")
      val subPathOpt = parser.parseOption(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("deps")
      val deps = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("targets")
      val targets = parser.parseISZ(parseTargetType _)
      parser.parseObjectNext()
      parser.parseObjectKey("ivyDeps")
      val ivyDeps = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("sources")
      val sources = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("resources")
      val resources = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("testSources")
      val testSources = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      parser.parseObjectKey("testResources")
      val testResources = parser.parseISZ(parser.parseString _)
      parser.parseObjectNext()
      return Module(id, basePath, subPathOpt, deps, targets, ivyDeps, sources, resources, testSources, testResources)
    }

    def eof(): B = {
      val r = parser.eof()
      return r
    }

  }

  def to[T](s: String, f: Parser => T): Either[T, Json.ErrorMsg] = {
    val parser = Parser(s)
    val r = f(parser)
    parser.eof()
    parser.errorOpt match {
      case Some(e) => return Either.Right(e)
      case _ => return Either.Left(r)
    }
  }

  def fromProject(o: Project, isCompact: B): String = {
    val st = Printer.printProject(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toProject(s: String): Either[Project, Json.ErrorMsg] = {
    def fProject(parser: Parser): Project = {
      val r = parser.parseProject()
      return r
    }
    val r = to(s, fProject _)
    return r
  }

  def fromModule(o: Module, isCompact: B): String = {
    val st = Printer.printModule(o)
    if (isCompact) {
      return st.renderCompact
    } else {
      return st.render
    }
  }

  def toModule(s: String): Either[Module, Json.ErrorMsg] = {
    def fModule(parser: Parser): Module = {
      val r = parser.parseModule()
      return r
    }
    val r = to(s, fModule _)
    return r
  }

}