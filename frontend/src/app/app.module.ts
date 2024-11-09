import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ChatComponent } from './chat/chat.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        CommonModule,
        HttpClientModule
    ],
    providers: []
})
export class AppModule { }