/********************************************************************************
 * Copyright (c) 2022 T-Systems International GmbH
 * Copyright (c) 2022 Contributors to the CatenaX (ng) GitHub Organisation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

package net.catenax.autosetup.vault.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

public class VaultConfiguration {

	@Bean(name = "valtrequestinterceptor")
	public VaultRequestInterceptor appRequestInterceptor() {
		return new VaultRequestInterceptor();
	}
}

@Slf4j
class VaultRequestInterceptor implements RequestInterceptor {

	@Value("${vault.token}")
	private String token;

	@Override
	public void apply(RequestTemplate template) {
		template.header("Authorization", "Bearer " + token);
		log.debug("Bearer authentication applied for vault");
	}

}