#!/bin/sh

./sbt "project book" paradox
rm -rf docs/*
cp -r book/target/paradox/site/main/* docs/
