/*
 * #%L
 * Comparing the Performance of some List Implementations
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 - 2015 Java Creed
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.javacreed.examples.comparator.part5;

import java.util.Comparator;

public class StudentGradeComparator implements Comparator<Student> {

  public static final StudentGradeComparator ASC = new StudentGradeComparator(1);

  public static final StudentGradeComparator DESC = new StudentGradeComparator(-1);

  private final int order;

  private StudentGradeComparator(final int order) {
    this.order = order;
  }

  @Override
  public int compare(final Student a, final Student b) {
    return order * Integer.compare(a.getGrade(), b.getGrade());
  }

}
