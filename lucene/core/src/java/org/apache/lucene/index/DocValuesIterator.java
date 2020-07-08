/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.index;

import java.io.IOException;

import org.apache.lucene.search.DocIdSetIterator;

abstract class DocValuesIterator extends DocIdSetIterator {

  protected DocIdSetIterator input;
  protected long seekTimeDocValues;
  protected int seekCountDocValues;

  public long getSeekTimeDocValues() {
    return seekTimeDocValues;
  }

  public void setSeekTimeDocValues(long seekTimeDocValues) {
    this.seekTimeDocValues = seekTimeDocValues;
  }

  public int getSeekCountDocValues() {
    return seekCountDocValues;
  }

  public void setSeekCountDocValues(int seekCountDocValues) {
    this.seekCountDocValues = seekCountDocValues;
  }

  public DocIdSetIterator getInput() {
    return input;
  }

  public void setInput(DocIdSetIterator input) {
    this.input = input;
  }

  public void reset() {
    seekCountDocValues = 0;
    seekTimeDocValues = 0;
    if(input != null) {
      input.setSeekCountDocValues(0);
    }
    if(input != null) {
      input.setSeekTimeDocValues(0);
    }
  }

  /** Advance the iterator to exactly {@code target} and return whether
   *  {@code target} has a value.
   *  {@code target} must be greater than or equal to the current
   *  {@link #docID() doc ID} and must be a valid doc ID, ie. &ge; 0 and
   *  &lt; {@code maxDoc}.
   *  After this method returns, {@link #docID()} returns {@code target}. */
  public abstract boolean advanceExact(int target) throws IOException;

}
