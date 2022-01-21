package com.sinaukoding.perpustakaan.controller;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("permitAll()")
public abstract class BaseController {
}
