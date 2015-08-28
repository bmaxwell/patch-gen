/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.patching.generator;

import java.util.Collection;
import java.util.Set;

import org.jboss.as.patching.metadata.ContentItem;
import org.jboss.as.patching.metadata.Patch;

/**
 * Configuration for generating a patch.
 *
 * @author Brian Stansberry (c) 2012 Red Hat Inc.
 */
public interface PatchConfig {

    /**
     * Get the unique patch ID.
     *
     * @return the patch id
     */
    String getPatchId();

    /**
     * Get the patch description.
     *
     * @return the patch description
     */
    String getDescription();

    /**
     * Get the patch type.
     *
     * @return the type of the patch
     */
    Patch.PatchType getPatchType();

    /**
     * Get the product name this patch applies to.
     *
     * @return the product name
     */
    String getAppliesToProduct();

    /**
     * Get the applies to version.
     *
     * @return the applies to
     */
    String getAppliesToVersion();

    /**
     * Get the resulting version for cumulative patches.
     *
     * @return the resulting version
     */
    String getResultingVersion();

    /**
     * Get the miscellaneous content item that will be in active use on a target process that isn't in admin-only mode.
     *
     * @return the items. Will not return {@code null}
     */
    Set<String> getInRuntimeUseItems();

    /**
     * Gets whether the patch.xml should be generated by differencing the two distributions.
     *
     * @return {@code true} if the patch.xml should be generated, {@code false} if they have been
     *         specified in the patch config document
     */
    boolean isGenerateByDiff();

    /**
     * Gets the modifications specifically specified in the patch config, if the config doesn't specify
     * {@link #isGenerateByDiff() generating the modifications by differencing the two distributions}.
     *
     * @return the modified content items
     */
    Set<ContentItem> getSpecifiedContent();

    /**
     * Get the configuration for each element.
     *
     * @return the patch elements
     */
    Collection<PatchElementConfig> getElements();

    /**
     * Returns a collection of optional paths. I.e. filesystem paths for which
     * patch updates should be generated and included but that might be missing
     * in the target installation and in that case be skipped instead of aborting
     * the whole patch altogether.
     *
     * @return  optional paths
     */
    Collection<OptionalPath> getOptionalPaths();

    /**
     * Create a {@link PatchBuilderWrapper} whose basic metadata matches what's configured in this object.
     *
     * @return the patch builder
     */
    PatchBuilderWrapper toPatchBuilder();

}
