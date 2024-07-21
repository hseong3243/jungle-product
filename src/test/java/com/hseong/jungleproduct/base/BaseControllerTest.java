package com.hseong.jungleproduct.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hseong.jungleproduct.service.LoginService;
import com.hseong.jungleproduct.service.OrderService;
import com.hseong.jungleproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ProductService productService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected LoginService loginService;
}
