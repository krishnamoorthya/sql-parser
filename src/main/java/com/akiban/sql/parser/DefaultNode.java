/**
 * Copyright © 2012 Akiban Technologies, Inc.  All rights
 * reserved.
 *
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This program may also be available under different license terms.
 * For more information, see www.akiban.com or contact
 * licensing@akiban.com.
 *
 * Contributors:
 * Akiban Technologies, Inc.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.DefaultNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * DefaultNode represents a column/parameter default.
 */
public  class DefaultNode extends ValueNode
{
    private String columnName;
    private String defaultText;
    private ValueNode defaultTree;

    /**
     * Initializer for a column/parameter default.
     *
     * @param defaultTree Query tree for default
     * @param defaultText The text of the default.
     */
    public void init(Object defaultTree,
                     Object defaultText) {
        this.defaultTree = (ValueNode)defaultTree;
        this.defaultText = (String)defaultText;
    }

    /**
     * Initializer for insert/update
     *
     */
    public void init(Object columnName) {
        this.columnName = (String)columnName;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        DefaultNode other = (DefaultNode)node;
        this.columnName = other.columnName;
        this.defaultText = other.defaultText;
        this.defaultTree = (ValueNode)getNodeFactory().copyNode(other.defaultTree,
                                                                getParserContext());
    }

    /**
     * Get the text of the default.
     */
    public String getDefaultText() {
        return defaultText;
    }

    /**
     * Get the query tree for the default.
     *
     * @return The query tree for the default.
     */
    public ValueNode getDefaultTree() {
        return defaultTree;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return "columnName: " + columnName + "\n" +
            "defaultText: " + defaultText + "\n" +
            super.toString();
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */
    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (defaultTree != null) {
            printLabel(depth, "defaultTree:");
            defaultTree.treePrint(depth + 1);
        }
    }

    /**
     * @inheritDoc
     */
    protected boolean isEquivalent(ValueNode other) {
        return false;
    }
}
