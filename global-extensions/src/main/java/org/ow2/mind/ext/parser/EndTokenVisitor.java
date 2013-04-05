/**
 * Copyright (C) 2010-2011 STMicroelectronics
 *
 * This file is part of "Mind Compiler" is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact: mind@ow2.org
 *
 * Authors: Matthieu Leclercq
 * Contributors: 
 */

package org.ow2.mind.ext.parser;

import org.ow2.mind.ext.jtb.syntaxtree.EXTFile;
import org.ow2.mind.ext.jtb.syntaxtree.AnnotationAnnotationValue;
import org.ow2.mind.ext.jtb.syntaxtree.AnnotationParameters;
import org.ow2.mind.ext.jtb.syntaxtree.AnnotationValue;
import org.ow2.mind.ext.jtb.syntaxtree.AnnotationValuePair;
import org.ow2.mind.ext.jtb.syntaxtree.AnnotationValuePairs;
import org.ow2.mind.ext.jtb.syntaxtree.Annotations;
import org.ow2.mind.ext.jtb.syntaxtree.ArchitectureDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.Argument;
import org.ow2.mind.ext.jtb.syntaxtree.ArgumentAssignement;
import org.ow2.mind.ext.jtb.syntaxtree.ArgumentList;
import org.ow2.mind.ext.jtb.syntaxtree.ArrayAnnotationValue;
import org.ow2.mind.ext.jtb.syntaxtree.AttributeDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.AttributeType;
import org.ow2.mind.ext.jtb.syntaxtree.AttributeValue;
import org.ow2.mind.ext.jtb.syntaxtree.BindingComponentName;
import org.ow2.mind.ext.jtb.syntaxtree.BindingDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.BooleanValue;
import org.ow2.mind.ext.jtb.syntaxtree.CompositeAnonymousDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.CompositeAnonymousExtension;
import org.ow2.mind.ext.jtb.syntaxtree.CompositeDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.CompositeDefinitionBody;
import org.ow2.mind.ext.jtb.syntaxtree.CompositeDefinitionElement;
import org.ow2.mind.ext.jtb.syntaxtree.CompositeDefinitionReference;
import org.ow2.mind.ext.jtb.syntaxtree.CompoundAttributeValue;
import org.ow2.mind.ext.jtb.syntaxtree.CompoundAttributeValueField;
import org.ow2.mind.ext.jtb.syntaxtree.CompoundFieldName;
import org.ow2.mind.ext.jtb.syntaxtree.DataDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.DataField;
import org.ow2.mind.ext.jtb.syntaxtree.DataFile;
import org.ow2.mind.ext.jtb.syntaxtree.ExtendedCompositeDefinitions;
import org.ow2.mind.ext.jtb.syntaxtree.ExtendedPrimitiveDefinitions;
import org.ow2.mind.ext.jtb.syntaxtree.ExtendedTypeDefinitions;
import org.ow2.mind.ext.jtb.syntaxtree.FlowInterfaceDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.FlowType;
import org.ow2.mind.ext.jtb.syntaxtree.FormalParameterDeclaration;
import org.ow2.mind.ext.jtb.syntaxtree.FormalParameterDeclarationList;
import org.ow2.mind.ext.jtb.syntaxtree.FormalTypeParameterDeclaration;
import org.ow2.mind.ext.jtb.syntaxtree.FormalTypeParameterDeclarationList;
import org.ow2.mind.ext.jtb.syntaxtree.FullyQualifiedName;
import org.ow2.mind.ext.jtb.syntaxtree.FunctionalInterfaceDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.IDTType;
import org.ow2.mind.ext.jtb.syntaxtree.ImplementationDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.ImportDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.IntegerValue;
import org.ow2.mind.ext.jtb.syntaxtree.InterfaceDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.NodeList;
import org.ow2.mind.ext.jtb.syntaxtree.NodeListOptional;
import org.ow2.mind.ext.jtb.syntaxtree.NodeOptional;
import org.ow2.mind.ext.jtb.syntaxtree.NodeSequence;
import org.ow2.mind.ext.jtb.syntaxtree.NodeToken;
import org.ow2.mind.ext.jtb.syntaxtree.NullValue;
import org.ow2.mind.ext.jtb.syntaxtree.Path;
import org.ow2.mind.ext.jtb.syntaxtree.PathValue;
import org.ow2.mind.ext.jtb.syntaxtree.PrimitiveAnonymousDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.PrimitiveAnonymousExtension;
import org.ow2.mind.ext.jtb.syntaxtree.PrimitiveDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.PrimitiveDefinitionBody;
import org.ow2.mind.ext.jtb.syntaxtree.PrimitiveDefinitionElement;
import org.ow2.mind.ext.jtb.syntaxtree.PrimitiveDefinitionReference;
import org.ow2.mind.ext.jtb.syntaxtree.ReferenceValue;
import org.ow2.mind.ext.jtb.syntaxtree.SimpleSubComponentReference;
import org.ow2.mind.ext.jtb.syntaxtree.StringValue;
import org.ow2.mind.ext.jtb.syntaxtree.SubComponentDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.TypeArgument;
import org.ow2.mind.ext.jtb.syntaxtree.TypeArgumentAssignement;
import org.ow2.mind.ext.jtb.syntaxtree.TypeArgumentList;
import org.ow2.mind.ext.jtb.syntaxtree.TypeDefinition;
import org.ow2.mind.ext.jtb.syntaxtree.TypeDefinitionBody;
import org.ow2.mind.ext.jtb.syntaxtree.TypeDefinitionElement;
import org.ow2.mind.ext.jtb.syntaxtree.TypeDefinitionReference;
import org.ow2.mind.ext.jtb.visitor.GJNoArguDepthFirst;

public class EndTokenVisitor extends GJNoArguDepthFirst<NodeToken> {

  // ---------------------------------------------------------------------------
  // Generic nodes
  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final NodeList n) {
    for (int i = n.size() - 1; i >= 0; i--) {
      final NodeToken t = n.elementAt(i).accept(this);
      if (t != null) return t;
    }
    return null;
  }

  @Override
  public NodeToken visit(final NodeSequence n) {
    for (int i = n.size() - 1; i >= 0; i--) {
      final NodeToken t = n.elementAt(i).accept(this);
      if (t != null) return t;
    }
    return null;
  }

  @Override
  public NodeToken visit(final NodeListOptional n) {
    for (int i = n.size() - 1; i >= 0; i--) {
      final NodeToken t = n.elementAt(i).accept(this);
      if (t != null) return t;
    }
    return null;
  }

  @Override
  public NodeToken visit(final NodeOptional n) {
    if (n.present()) {
      return n.node.accept(this);
    }
    return null;
  }

  @Override
  public NodeToken visit(final NodeToken n) {
    return n;
  }

  // ---------------------------------------------------------------------------
  // FQN
  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final FullyQualifiedName n) {
    final NodeToken t = n.f1.accept(this);
    if (t != null) return t;

    return (NodeToken) n.f0.choice;
  }

  // ---------------------------------------------------------------------------
  // File level grammar
  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final EXTFile n) {
    return n.f2;
  }

  @Override
  public NodeToken visit(final ImportDefinition n) {
    final NodeToken t = n.f6.accept(this);
    if (t != null) return t;

    return n.f5.accept(this);
  }

  @Override
  public NodeToken visit(final ArchitectureDefinition n) {
    return n.f0.accept(this);
  }

  // ---------------------------------------------------------------------------
  // Definition prototypes grammar
  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final TypeDefinition n) {
    final NodeToken t = n.f3.accept(this);
    if (t != null) return t;

    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final ExtendedTypeDefinitions n) {
    final NodeToken t = n.f2.accept(this);
    if (t != null) return t;
    return n.f1.accept(this);
  }

  @Override
  public NodeToken visit(final TypeDefinitionReference n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final TypeDefinitionBody n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final TypeDefinitionElement n) {
    return n.f0.accept(this);
  }

  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final PrimitiveDefinition n) {
    NodeToken t = n.f5.accept(this);
    if (t != null) return t;

    t = n.f4.accept(this);
    if (t != null) return t;

    return n.f3.accept(this);
  }

  @Override
  public NodeToken visit(final FormalParameterDeclarationList n) {
    return n.f2;
  }

  @Override
  public NodeToken visit(final FormalParameterDeclaration n) {
    return n.f0;
  }

  @Override
  public NodeToken visit(final ExtendedPrimitiveDefinitions n) {
    final NodeToken t = n.f2.accept(this);
    if (t != null) return t;

    return n.f1.accept(this);
  }

  @Override
  public NodeToken visit(final PrimitiveDefinitionReference n) {
    final NodeToken t = n.f1.accept(this);
    if (t != null) return t;

    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final ArgumentList n) {
    return n.f2;
  }

  @Override
  public NodeToken visit(final ArgumentAssignement n) {
    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final Argument n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final PrimitiveDefinitionBody n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final PrimitiveDefinitionElement n) {
    return n.f0.accept(this);
  }

  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final CompositeDefinition n) {
    NodeToken t = n.f5.accept(this);
    if (t != null) return t;

    t = n.f4.accept(this);
    if (t != null) return t;

    t = n.f3.accept(this);
    if (t != null) return t;

    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final FormalTypeParameterDeclarationList n) {
    return n.f3;
  }

  @Override
  public NodeToken visit(final FormalTypeParameterDeclaration n) {
    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final CompositeDefinitionReference n) {
    NodeToken t = n.f2.accept(this);
    if (t != null) return t;

    t = n.f1.accept(this);
    if (t != null) return t;

    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final TypeArgumentList n) {
    return n.f2;
  }

  @Override
  public NodeToken visit(final TypeArgumentAssignement n) {
    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final TypeArgument n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final ExtendedCompositeDefinitions n) {
    final NodeToken t = n.f2.accept(this);
    if (t != null) return t;

    return n.f1.accept(this);
  }

  @Override
  public NodeToken visit(final CompositeDefinitionBody n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final CompositeDefinitionElement n) {
    return n.f0.accept(this);
  }

  // ---------------------------------------------------------------------------
  // Content grammar
  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final InterfaceDefinition n) {
    return n.f1.accept(this);
  }

  @Override
  public NodeToken visit(final FunctionalInterfaceDefinition n) {
    NodeToken t = n.f6.accept(this);
    if (t != null) return t;

    t = n.f5.accept(this);
    if (t != null) return t;

    return (NodeToken) n.f4.choice;
  }

  @Override
  public NodeToken visit(final FlowInterfaceDefinition n) {
    NodeToken t = n.f6.accept(this);
    if (t != null) return t;

    t = n.f5.accept(this);
    if (t != null) return t;

    return (NodeToken) n.f4.choice;
  }

  @Override
  public NodeToken visit(final FlowType n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final IDTType n) {
    return n.f3.accept(this);
  }

  @Override
  public NodeToken visit(final AttributeDefinition n) {
    NodeToken t = n.f5.accept(this);
    if (t != null) return t;

    t = n.f4.accept(this);
    if (t != null) return t;

    return n.f3;
  }

  @Override
  public NodeToken visit(final AttributeType n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final AttributeValue n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final CompoundAttributeValue n) {
    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final CompoundAttributeValueField n) {
    return n.f1.accept(this);
  }

  @Override
  public NodeToken visit(final CompoundFieldName n) {
    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final DataDefinition n) {
    final NodeToken t = n.f3.accept(this);
    if (t != null) return t;

    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final DataField n) {
    return n.f1.accept(this);
  }

  @Override
  public NodeToken visit(final DataFile n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final ImplementationDefinition n) {
    final NodeToken t = n.f3.accept(this);
    if (t != null) return t;

    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final Path n) {
    if (n.f5.present()) {
      return (NodeToken) ((NodeSequence) n.f5.node).nodes.elementAt(1);
    } else if (n.f4.present()) {
      return (NodeToken) ((NodeSequence) (n.f4.nodes.lastElement()))
          .elementAt(1);
    } else {
      return (NodeToken) n.f3.choice;
    }
  }

  @Override
  public NodeToken visit(final BindingDefinition n) {
    NodeToken t = n.f11.accept(this);
    if (t != null) return t;

    t = n.f10.accept(this);
    if (t != null) return t;

    return (NodeToken) n.f9.choice;
  }

  @Override
  public NodeToken visit(final BindingComponentName n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final SubComponentDefinition n) {
    final NodeToken t = n.f3.accept(this);
    if (t != null) return t;

    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final SimpleSubComponentReference n) {
    final NodeToken t = n.f3.accept(this);
    if (t != null) return t;

    return (NodeToken) n.f2.choice;
  }

  @Override
  public NodeToken visit(final CompositeAnonymousDefinition n) {
    return n.f1;
  }

  @Override
  public NodeToken visit(final PrimitiveAnonymousDefinition n) {
    return n.f1;
  }

  @Override
  public NodeToken visit(final PrimitiveAnonymousExtension n) {
    return n.f1;
  }

  @Override
  public NodeToken visit(final CompositeAnonymousExtension n) {
    return n.f1;
  }

  // ---------------------------------------------------------------------------
  // Annotation grammar
  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final Annotations n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final org.ow2.mind.ext.jtb.syntaxtree.Annotation n) {
    final NodeToken t = n.f2.accept(this);
    if (t != null) return t;

    return n.f1.accept(this);
  }

  @Override
  public NodeToken visit(final AnnotationParameters n) {
    return n.f2;
  }

  @Override
  public NodeToken visit(final AnnotationValuePairs n) {
    final NodeToken t = n.f1.accept(this);
    if (t != null) return t;

    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final AnnotationValuePair n) {
    return n.f2.accept(this);
  }

  @Override
  public NodeToken visit(final AnnotationValue n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final AnnotationAnnotationValue n) {
    return n.f0.accept(this);
  }

  @Override
  public NodeToken visit(final ArrayAnnotationValue n) {
    return n.f2;
  }

  // ---------------------------------------------------------------------------
  // Value grammar
  // ---------------------------------------------------------------------------

  @Override
  public NodeToken visit(final StringValue n) {
    return n.f0;
  }

  @Override
  public NodeToken visit(final IntegerValue n) {
    return n.f1;
  }

  @Override
  public NodeToken visit(final BooleanValue n) {
    return n.f0;
  }

  @Override
  public NodeToken visit(final ReferenceValue n) {
    return n.f0;
  }

  @Override
  public NodeToken visit(final NullValue n) {
    return n.f0;
  }

  @Override
  public NodeToken visit(final PathValue n) {
    return n.f0.accept(this);
  }

}
