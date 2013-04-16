tapestry-caching
=================

Based on ioko tapestry-commons project, check https://github.com/toby78/ioko-tapestry-commons for further information.

Works with Tapestry 5.3.

## cache/Container component

This component provides caching of the generated HTML for a page snippet in EHCache. This is mainly useful if you have expensive (to render) areas of a page that you cannot fully cache (e.g. if the page is personalised).

Using ehcache as the cache backed gives you many options for configuration and management (e.g. you can use JMX to flush the caches).

### How to use it?

Add this dependency in the pom.xml of your project:

<dependency>
	<groupId>uk.co.ioko</groupId>
	<artifactId>tapestry-caching</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>

Then, simply place the caching tag around your cachable content in your TML files.

<div t:id="cached" t:type="cache/Container">
    <dt>Time in cache</dt>
    <dd>${now}</dd>
</div>

This will cache the content according for a 'medium' length of time.

You can specify short, medium or long cache times via the cacheRegion parameter

If your component renders different content depending on context you need to specify a cacheSuffix parameter to differentiate them.

<div t:id="personallyCached" t:type="cache/Container" t:cacheSuffix="username">
    <dt>Hi ${username} Time in cache</dt>
    <dd>${now}</dd>
</div>

### Configuration

You don't need to configure the cache, but if you want to you simply create a '{{{https://github.com/ioko-tapestry-commons/ioko-tapestry-commons/blob/master/tapestry-commons/tapestry-caching/src/main/resources/ehcacheTapestryContent-fallback.xml}ehcacheTapestryContent.xml}} and set the cache regions to cache as you want. This is a standard {{{http://ehcache.sourceforge.net/}EHCache}} configuration file.

The components requires cache's called

 - content-short

 - content-medium

 - content-long

### Notes

You must make sure you application server is NOT generating 'cookieless sessions' if your cached area includes any hyperlinks.