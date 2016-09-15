/**
 * Copyright (c) 2013 CEA LIST.
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 */
package org.eclipse.papyrus.designer.transformation.languages.cpp.library;

import com.google.common.base.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include;
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.LibraryUtils;
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.core.Messages;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.library.transformations.AbstractBootLoaderGen;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

/**
 * Create a BootLoader for C++
 */
@SuppressWarnings("all")
public class BootLoaderGenCpp extends AbstractBootLoaderGen implements IM2MTrafoCDP {
  public final static URI CPP_BOOTLOADER_URI = URI.createURI("pathmap://DML_TRAFOS_CPP/BootLoaderCpp.uml");
  
  public final static String CPP_BOOTLOADER_QNAME = "BootLoaderCpp::BootLoader";
  
  public final static String CPP_NODEINFO_QNAME = "BootLoaderCpp::NodeInfo";
  
  /**
   * Create a new boot-loader in a specific package
   * (which represents a node of the system).
   * 
   * @param copier a lazy copier
   * @param nodeIndex the index of the node
   * @param numberOfNodes the number of nodes
   * @throws TransformationException
   */
  @Override
  public void init(final LazyCopier copier, final int nodeIndex, final int numberOfNodes) throws TransformationException {
    final org.eclipse.uml2.uml.Package root = TransformationContext.current.modelRoot;
    this.outputSizeof = false;
    this.m_copier = copier;
    ResourceSet _resourceSet = ModelManagement.getResourceSet();
    Element _content = LibraryUtils.getContent(BootLoaderGenCpp.CPP_BOOTLOADER_URI, _resourceSet);
    final org.eclipse.uml2.uml.Package bootloader_ml = ((org.eclipse.uml2.uml.Package) _content);
    boolean _equals = Objects.equal(bootloader_ml, null);
    if (_equals) {
      String _format = String.format(
        Messages.BootLoaderGen_CannotRetrieveTemplate, BootLoaderGenCpp.CPP_BOOTLOADER_URI);
      throw new TransformationException(_format);
    }
    NamedElement _qualifiedElement = ElementUtils.getQualifiedElement(bootloader_ml, BootLoaderGenCpp.CPP_BOOTLOADER_QNAME);
    final org.eclipse.uml2.uml.Class template = ((org.eclipse.uml2.uml.Class) _qualifiedElement);
    boolean _equals_1 = Objects.equal(template, null);
    if (_equals_1) {
      String _format_1 = String.format(
        Messages.BootLoaderGen_CannotRetrieveTemplate, BootLoaderGenCpp.CPP_BOOTLOADER_QNAME);
      throw new TransformationException(_format_1);
    }
    Element _owner = template.getOwner();
    copier.put(_owner, root);
    Element _owner_1 = template.getOwner();
    copier.setStatus(_owner_1, LazyCopier.CopyStatus.INPROGRESS);
    org.eclipse.uml2.uml.Class _copy = copier.<org.eclipse.uml2.uml.Class>getCopy(template);
    this.m_bootLoader = _copy;
    final Include cppInclude = StereotypeUtil.<Include>applyApp(this.m_bootLoader, Include.class);
    boolean _equals_2 = Objects.equal(cppInclude, null);
    if (_equals_2) {
      throw new TransformationException(Messages.BootLoaderGen_CannotApplyCppInclude);
    }
    final String existingBody = cppInclude.getBody();
    String bodyStr = AbstractBootLoaderGen.EMPTYSTR;
    if (this.outputSizeof) {
      String _bodyStr = bodyStr;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#include <iostream>");
      _builder.newLine();
      _builder.append("using namespace std");
      _builder.newLine();
      bodyStr = (_bodyStr + _builder);
    }
    cppInclude.setBody((existingBody + bodyStr));
    NamedElement _qualifiedElement_1 = ElementUtils.getQualifiedElement(bootloader_ml, BootLoaderGenCpp.CPP_NODEINFO_QNAME);
    final org.eclipse.uml2.uml.Class nodeInfo = ((org.eclipse.uml2.uml.Class) _qualifiedElement_1);
    final Property nodeIndexElem = nodeInfo.getAttribute("nodeIndex", null);
    final Property numberOfNodesElem = nodeInfo.getAttribute("numberOfNodes", null);
    ValueSpecification _defaultValue = nodeIndexElem.getDefaultValue();
    ((LiteralInteger) _defaultValue).setValue((nodeIndex + 0));
    ValueSpecification _defaultValue_1 = numberOfNodesElem.getDefaultValue();
    ((LiteralInteger) _defaultValue_1).setValue(numberOfNodes);
    copier.<org.eclipse.uml2.uml.Class>getCopy(nodeInfo);
    this.m_initCode = AbstractBootLoaderGen.EMPTYSTR;
    this.m_initCodeRun = AbstractBootLoaderGen.EMPTYSTR;
    HashMap<org.eclipse.uml2.uml.Class, EList<String>> _hashMap = new HashMap<org.eclipse.uml2.uml.Class, EList<String>>();
    this.m_activation = _hashMap;
    this.m_initCodeCConnections = AbstractBootLoaderGen.EMPTYSTR;
    this.m_initCodeCConfig = AbstractBootLoaderGen.EMPTYSTR;
    if (this.outputSizeof) {
    }
  }
  
  @Override
  public String languageCreateInstance(final InstanceSpecification is, final org.eclipse.uml2.uml.Class implementation) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  @Override
  public String languageCreateConn(final String varName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(varName, "");
    _builder.append(".createConnections();");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  @Override
  public String languageRunStart(final String varName, final boolean useOO) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(varName, "");
    _builder.append(".get_start()->run();");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  @Override
  public String languageAssignRef(final String accessName, final String referenceVarName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(accessName, "");
    _builder.append(" = &");
    _builder.append(referenceVarName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  @Override
  public void instanceConfig(final Stack<Slot> slotPath, final InstanceSpecification instance) throws TransformationException {
    final Slot slot = slotPath.peek();
    final StructuralFeature sf = slot.getDefiningFeature();
    boolean _equals = Objects.equal(sf, null);
    if (_equals) {
      String _name = instance.getName();
      String _format = String.format("A slot for instance %s has no defining feature", _name);
      throw new TransformationException(_format);
    }
    String _name_1 = instance.getName();
    String _plus = (_name_1 + ".");
    String _name_2 = sf.getName();
    final String varName = (_plus + _name_2);
    EList<ValueSpecification> _values = slot.getValues();
    for (final ValueSpecification value : _values) {
      String _stringValue = value.stringValue();
      boolean _notEquals = (!Objects.equal(_stringValue, null));
      if (_notEquals) {
        String _m_initCodeCConfig = this.m_initCodeCConfig;
        String _stringValue_1 = value.stringValue();
        String _plus_1 = ((varName + " = ") + _stringValue_1);
        String _plus_2 = (_plus_1 + AbstractBootLoaderGen.EOL);
        this.m_initCodeCConfig = (_m_initCodeCConfig + _plus_2);
      }
    }
  }
  
  @Override
  public void applyTrafo(final M2MTrafo trafo, final org.eclipse.uml2.uml.Package deploymentPlan) throws TransformationException {
    this.init(TransformationContext.current.copier, 0, 0);
    final Stack<Slot> slotPath = new Stack<Slot>();
    EList<InstanceSpecification> _topLevelInstances = DepUtils.getTopLevelInstances(deploymentPlan);
    for (final InstanceSpecification is : _topLevelInstances) {
      this.addInstance(is, slotPath);
    }
    this.addInit("C/Cpp");
  }
  
  @Override
  public void languageInit() {
  }
  
  @Override
  public String languageActivation(final org.eclipse.uml2.uml.Class[] activationKeys) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// activation code");
    _builder.newLine();
    {
      for(final org.eclipse.uml2.uml.Class implementation : activationKeys) {
        final EList<String> varNameList = this.m_activation.get(implementation);
        _builder.newLineIfNotEmpty();
        {
          for(final String varName : varNameList) {
            _builder.append(varName, "");
            _builder.append(".get_lc()->activate();");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("return code;");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String languageDeactivation(final org.eclipse.uml2.uml.Class[] activationKeys) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// deactivation code (reverse order)");
    _builder.newLine();
    {
      List<org.eclipse.uml2.uml.Class> _reverse = ListExtensions.<org.eclipse.uml2.uml.Class>reverse(((List<org.eclipse.uml2.uml.Class>)Conversions.doWrapArray(activationKeys)));
      for(final org.eclipse.uml2.uml.Class implementation : _reverse) {
        final EList<String> varNameList = this.m_activation.get(implementation);
        _builder.newLineIfNotEmpty();
        {
          for(final String varName : varNameList) {
            _builder.append(varName, "");
            _builder.append(".get_lc()->deactivate();");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("return code;");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String languageDefaultExecCode() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// no user entry point => enter endless loop");
    _builder.newLine();
    _builder.append("for (;;) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("usleep(100);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
