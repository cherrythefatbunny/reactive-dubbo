var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "6000",
        "ok": "6000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "106",
        "ok": "106",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "10438",
        "ok": "10438",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "3234",
        "ok": "3234",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1283",
        "ok": "1283",
        "ko": "-"
    },
    "percentiles1": {
        "total": "3876",
        "ok": "3876",
        "ko": "-"
    },
    "percentiles2": {
        "total": "4051",
        "ok": "4051",
        "ko": "-"
    },
    "percentiles3": {
        "total": "4221",
        "ok": "4221",
        "ko": "-"
    },
    "percentiles4": {
        "total": "4726",
        "ok": "4726",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 480,
        "percentage": 8
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 177,
        "percentage": 3
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 5343,
        "percentage": 89
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "34.483",
        "ok": "34.483",
        "ko": "-"
    }
},
contents: {
"req_blocking-by101-c1872": {
        type: "REQUEST",
        name: "blocking_by101",
path: "blocking_by101",
pathFormatted: "req_blocking-by101-c1872",
stats: {
    "name": "blocking_by101",
    "numberOfRequests": {
        "total": "6000",
        "ok": "6000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "106",
        "ok": "106",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "10438",
        "ok": "10438",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "3234",
        "ok": "3234",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1283",
        "ok": "1283",
        "ko": "-"
    },
    "percentiles1": {
        "total": "3876",
        "ok": "3876",
        "ko": "-"
    },
    "percentiles2": {
        "total": "4051",
        "ok": "4051",
        "ko": "-"
    },
    "percentiles3": {
        "total": "4221",
        "ok": "4221",
        "ko": "-"
    },
    "percentiles4": {
        "total": "4726",
        "ok": "4726",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 480,
        "percentage": 8
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 177,
        "percentage": 3
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 5343,
        "percentage": 89
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "34.483",
        "ok": "34.483",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
