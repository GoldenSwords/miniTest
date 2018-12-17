/*
 Copyright 2006 Jerry Huxtable

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/*
 * This file was semi-automatically converted from the public-domain USGS PROJ source.
 */
package org.meteoinfo.projection.proj4j.proj;

public class Murdoch3Projection extends SimpleConicProjection {

    public Murdoch3Projection() {
        super(SimpleConicProjection.MURD3);
        proj4Name = "murd3";
        name = "Murdoch_III";
    }

    @Override
    public String toString() {
        return "Murdoch III";
    }
}
