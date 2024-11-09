// main.ts
import { bootstrapApplication } from '@angular/platform-browser';
import { ChatComponent } from './app/chat/chat.component';
import { importProvidersFrom } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

bootstrapApplication(ChatComponent, {
  providers: [
    importProvidersFrom(BrowserModule, FormsModule, CommonModule, HttpClientModule)
  ]
}).catch(err => console.error(err));