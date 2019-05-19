package net.gaotao.productservice.domain;

import java.io.Serializable;

/**
 * 产品实现序列化
 */
public class Product implements Serializable {

 private int id;
 private String name;
 private int store;
 private int price;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public int getStore() {
  return store;
 }

 public void setStore(int store) {
  this.store = store;
 }

 public int getPrice() {
  return price;
 }

 public void setPrice(int price) {
  this.price = price;
 }

 public Product() {
 }

 public Product(int id, String name, int store, int price) {
  this.id = id;
  this.name = name;
  this.store = store;
  this.price = price;
 }

}
